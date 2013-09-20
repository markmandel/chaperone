(ns chaperone.user-test
	(:use [midje.sweet]
		  [chaperone.user])
	(:require [test-helper :as test]
			  [clj-time.core :as time]
			  [clojurewerkz.elastisch.rest.index :as esi]
			  [chaperone.persistence.install :as install]
			  [chaperone.persistence.core :as pcore]))

(defn- setup!
	   "Provides setup for the tests. Has side effects"
	   []
	   (test/stop)
	   (test/create)
	   (test/start pcore/start))

(namespace-state-changes (before :facts (setup!)))

(fact
	"Better constructor works"
	(let [test-user (new-user "Mark" "Mandel" "email" "password")]
		(:id test-user) => truthy
		(:firstname test-user) => "Mark"
		(:lastname test-user) => "Mandel"
		(:password test-user) => "password"
		(:email test-user) => "email"
		(:photo test-user) => nil
		(:last-logged-in test-user) => nil
		)
	)

(fact
	"Test optional arguments work"
	(let [test-user (new-user "Mark" "Mandel" "email" "password" :photo "photo" :last-logged-in time/now)]
		(:id test-user) => truthy
		(:firstname test-user) => "Mark"
		(:photo test-user) => "photo"
		(:last-logged-in test-user) => truthy
		)
	)

(fact
	"Persistance methods work correctly"
	(let [test-user (new-user "Mark" "Mandel" "email" "password")]
		(pcore/get-type test-user) => "user"))

(fact
	"Test if the _source->User works properly"
	(esi/delete @test/es-index)
	(install/create-index test/system)
	(let [test-user (new-user "Mark" "Mandel" "email" "password" :last-logged-in (time/now) :photo "photo.jpg")
		  persistence (pcore/sub-system test/system)]
		(pcore/create persistence test-user)
		(let [_source->User (partial _source->User persistence)
			  result (-> (pcore/get-by-id persistence "user" (:id test-user)) :_source _source->User)]
			(doseq [key (keys result)]
				(key test-user) => (key result))
			)
		))

(fact "Be able to list all users"
	  (let [test-user1 (new-user "Mark" "Mandel" "email" "password")
			test-user2 (new-user "ZAardvark" "ZAbigail" "email" "password")
			persistence (pcore/sub-system test/system)]
		  (esi/delete @test/es-index)
		  (install/create-index test/system)
		  (doto persistence
			  (pcore/create test-user1)
			  (pcore/create test-user2))
		  (esi/refresh @test/es-index)
		  (list-users persistence) => [test-user1 test-user2]))