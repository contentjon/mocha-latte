(ns latte.test.core
  (:require-macros [latte.core :as l]))

(def assert (js/require "assert"))

(l/describe "Test Suites"
  
  (l/it "can contain test cases" []
    (assert (= true true)))
  
  (l/it "can contain empty (pending) test cases" [])
            
  (l/it "can skip test cases" [] :skip true
    (assert (= true false)))
  
  (l/it "can contain asynchronous test cases" [done]
    (js/setTimeout done 0))
            
  (l/it "can contain asynchronous test cases with a timeout" [done] :timeout 500
    (js/setTimeout done 100))
            
  (l/describe "Nested Suites"
    (l/it "can contain test cases in nested test suites" []
      (assert (= true true)))))

(def test-value (atom nil))

(l/describe "Test fixtures"

  (l/describe "before"
            
    (l/before []
      (reset! test-value true))
    
    (l/it "before is called at the beginning of the suite" []
      (assert (= @test-value true))))
  
  (l/describe "after"
    
    (l/before []
      (reset! test-value true))
              
    (l/after []
      (reset! test-value false))
    
    (l/it "after is called at the end of the suite" []
      (assert (= @test-value true))))
  
  (l/describe "after (second pass)"
              
    (l/it "after is called at the end of the suite" []
      (assert (= @test-value false))))
  
  (l/describe "beforeEach"
            
    (l/before []
      (reset! test-value 0))
    
    (l/beforeEach []
      (swap! test-value inc))
              
    (l/it "beforeEach is called at the before first test case" []
      (assert (= @test-value 1)))
              
    (l/it "beforeEach is called at the before second test case" []
      (assert (= @test-value 2)))
              
    (l/it "beforeEach is called at the before third test case" []
      (assert (= @test-value 3))))
            
  (l/describe "afterEach"
            
    (l/before []
      (reset! test-value 0))
    
    (l/afterEach []
      (swap! test-value inc))
              
    (l/it "afterEach is called at the after first test case" []
      (assert (= @test-value 0)))
              
    (l/it "afterEach is called at the after second test case" []
      (assert (= @test-value 1)))
              
    (l/it "afterEach is called at the after third test case" []
      (assert (= @test-value 2))))
            
  (l/describe "that are asynchonous"
              
    (l/before [done]
      (js/setTimeout 
        (fn []
          (reset! test-value :foo)
          (done))
        0))
              
    (l/it "test case is run after asynchronous begin finishes" []
      (assert (= @test-value :foo)))))
