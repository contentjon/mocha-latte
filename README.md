mocha-latte
===========

This library wraps the Mocha JavaScript testing framework,
so it can be used with ClojureScript.

See the [Mocha Documentation](http://mochajs.org/) for a general
introduction.

```clojure
[mocha-latte "0.1.2"]
```

Since Mocha itself does not provide an assertion mechanism, you may want to
use our [ClojureScript wrapper](https://github.com/contentjon/chai-latte) for the
[Chai](http://chaijs.com/) assertion library to write actual tests.

Installation
------------

Before running tests, simply install mocha with npm.

    npm install mocha -g

Running Tests
-------------

To run tests, compile your unit test code down to JavaScript.
It is generally a good idea to let the closure compile do only __simple__ optimizations.
For an example setup see the project.clj in this repository.

When this is done, execute the test files by running mocha on them.

    mocha <path to tests>

Example
----------

```clojure
(ns mylib.test.core
  (:require-macros [latte.core :refer (describe it)]))

(describe "Fruits"

  (describe "Apples"

    (it "is red" []
      ;; assertion code
      )))
```

Test Suite options
------------------

Mocha Test Suites and Test Cases support several options, which can be supplied
as keyword arguments right before before the Suite or Test Case code.

```clojure
(describe "Test Suites"

  (describe "with options"

    (it "get skipped" [] :skip true
      ;; assertion code
      )

    (it "runs only this test case" [] :only true
      ;; assertion code
      )))
```

Asynchronous Tests
------------------

Mocha supports asynchronous tests by supplying a __done__ function to a test case.
This is replicated in this library.

```clojure
(describe "Asynchronous Test Suites"

  (it "runs asynchronously" [done]
    (js/setTimeout done 100))))
```


Additionally you may provide a timeout option to abort testing
at the Suite or Test Case level.

```clojure
(describe "Asynchronous Test Suites" :timeout 1000

  (it "times out after 500ms" [done] :timeout 500
    (js/setTimeout done 100))))
```

Test Fixtures
-------------

The regular Mocha __before__, __each__, __beforeEach__ and __afterEach__ Test Suite elements are supported,
following the same syntax as the __it__ macro. Test Fixture macros can be asynchronous via the
done parameter. No options are implemented for these macros.
