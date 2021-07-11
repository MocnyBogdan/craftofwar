(ns craftofwar.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [craftofwar.webfront :refer :all]
            [craftofwar.change :refer :all]
            [compojure.core :as c]
            [craftofwar.savestate :refer :all]
            [craftofwar.sample-data :as sample]
            [ring.middleware.params :as p]
            [medley.core :as medley]
            [environ.core :refer [env]]
            ))

(defn unwrapped-quest [req]
  (let [{:keys [params]} req
        name (get params "name")
        mob (keyword(get params "mob"))
        difficulty (read-string(get params "difficulty"))
        rewards$ (medley/assoc-some (medley/assoc-some {}
                                    (keyword(get params "quest reward 1"))
                                    (read-string(get params "value 1")))
                                    (keyword(get params "quest reward 2"))
                                    (read-string(or (get params "value 2") "nil")))
        ]
        (hash-map :name name :mob mob :difficulty difficulty :rewards rewards$)
        ))

(c/defroutes handler
             (c/GET  "/" [] (web @savestate))
             (c/GET  "/next_turn" [] (web (swap! savestate player-shift) ))
             (c/POST "/new_quest" [:as req] (web (swap! savestate #(assoc % :quests (conj (:quests %) (unwrapped-quest req))))))
             (c/GET  "/favicon.ico" [] {:status 404}))

(def app
  (-> handler
      p/wrap-params))

"If tests have been run the run needs to be terminated first, or you will get the double bind error"
(defonce server (jetty/run-jetty #'app {:port (Integer. (env :port)) :join? false}))

(defn -main [& args]
  (.start server)
 )