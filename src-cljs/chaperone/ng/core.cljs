(ns ^{:doc "The angularJS core implementation for the front end of this site"}
	chaperone.ng.core
	(:use [purnam.cljs :only [aget-in aset-in]])
	(:use-macros
		[purnam.js :only [obj !]]
		[purnam.angular :only [def.module def.config def.controller]]))

(def.module chaperone.app [ngRoute])

;; configure routes
(def.config chaperone.app [$routeProvider]
			(doto $routeProvider
				(.when "/admin/users/add" (obj :templateUrl "/public/partials/admin/user/user-form.html" :controller "AdminUserCtrl"))
				(.when "/admin/users/list" (obj :templateUrl "/public/partials/admin/user/list.html"))
				(.otherwise (obj :templateUrl "/public/partials/index.html"))))

(def.controller chaperone.app.AdminUserCtrl [$scope]
				(! $scope.title "Add"))