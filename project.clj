(defproject connect-clojure-api "1.0.3"

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.293"]]

  :plugins [[lein-cljsbuild "1.1.5"]]

  :source-paths ["src"]

  :clean-targets ["bundle.js"
                  "target/out"]

  :cljsbuild {
              :builds [{:source-paths ["src"]
                        :compiler {
                                   :output-to "bundle.js"
                                   :output-dir "target/bundle"
                                   :target :nodejs
                                   :optimizations :simple}}]})
