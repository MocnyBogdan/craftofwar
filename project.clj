(defproject craftofwar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.9.5"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler craftofwar.core/handler
          :auto-reload? true
          :auto-refresh? true}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring "1.9.3"]
                 [hiccup "1.0.5"]
                 [compojure "1.6.2"]
                 [environ "1.2.0"]
                 [medley "1.3.0"]]
  :main ^:skip-aot craftofwar.core
  :target-path "target/%s"
  :uberjar-name "craftofwar.jar"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

