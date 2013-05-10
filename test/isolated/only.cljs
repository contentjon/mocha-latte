(ns latte.test.only
  "This name space exists, so we can test the only feature.
   This needs to be compiled seprately because setting this
   lag skips all other tests in a file"
  (:require-macros [latte.core :as l]))

(def assert (js/require "assert"))

(l/describe "Test Suites"
            
  (l/describe "with an only flag"
              
    (l/it "run only one test case" [] :only true
      (assert (= true true)))
              
    (l/it "don't run this test case" []
      (assert (= true false)))))
