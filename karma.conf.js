// Karma configuration
// Generated on Thu Nov 21 2013 17:28:48 GMT+1100 (EST)

module.exports = function(config)
{
	config.set({

		// base path, that will be used to resolve files and exclude
		basePath: '',


		// frameworks to use
		frameworks: ['jasmine'],


		// list of files / patterns to load in the browser
		files: [
			'resources/public/js/jquery-2.0.3.min.js'
			,'resources/public/js/angular.min.js'
			,'resources/public/js/angular-route.min.js'
			, 'resources/public/js/test/angular-mocks.js'
			, 'resources/public/js/test/main.js'
			, 'resources/public/directives/*.html'
		],

		ngHtml2JsPreprocessor: {
			// strip this from the file path
			stripPrefix: 'resources'
			,moduleName: 'chaperone.app'
		},

		// list of files to exclude
		exclude: [

		],

		// test results reporter to use
		// possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
		reporters: ['progress','ubuntu'],

		// web server port
		port: 9876,


		// enable / disable colors in the output (reporters and logs)
		colors: true,


		// level of logging
		// possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
		logLevel: config.LOG_INFO,


		// enable / disable watching file and executing tests whenever any file changes
		autoWatch: true,


		// Start these browsers, currently available:
		// - Chrome
		// - ChromeCanary
		// - Firefox
		// - Opera (has to be installed with `npm install karma-opera-launcher`)
		// - Safari (only Mac; has to be installed with `npm install karma-safari-launcher`)
		// - PhantomJS
		// - IE (only Windows; has to be installed with `npm install karma-ie-launcher`)
		browsers: ['Chrome'],


		// If browser does not capture in given timeout [ms], kill it
		captureTimeout: 60000,


		// Continuous Integration mode
		// if true, it capture browsers, run tests and exit
		singleRun: false
	});
};
