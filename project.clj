(defproject pustota "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.cli "0.2.4"]
                 [org.clojure/core.async "0.1.242.0-44b1e3-alpha"]
                 [enlive "1.1.4"]]
  :main pustota.core
  :profiles {:uberjar {:aot :all}})



