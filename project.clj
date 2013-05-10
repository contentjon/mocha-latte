(defproject mocha-latte "0.2.0-SNAPSHOT"
  :description      "A ClojureScript port of the Mocha test frame work"
  :url              "https://github.com/contentjon/mocha-latte"
  :license          {:name         "MIT"
                     :url          "http://opensource.org/licenses/MIT"
                     :distribution :repo
                     :comments     "Same license as Mocha"}
  :min-lein-version "2.0.0"
  :plugins          [[lein-cljsbuild "0.3.0"]]
  :dependencies     [[org.clojure/clojure "1.4.0"]]
  :cljsbuild
  {:builds
   [{:source-paths ["test/latte" "src"]
     :id "unit"
     :compiler
     {:pretty-print  true
      :output-to     "test/unit.js"
      :optimizations :simple}}
    {:source-paths ["test/isolated" "src"]
     :id "only"
     :compiler
     {:pretty-print  true
      :output-to     "test/only.js"
      :optimizations :simple}}]})
