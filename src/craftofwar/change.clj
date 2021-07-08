(ns craftofwar.change
  (:require [craftofwar.world-config :refer :all]
            [craftofwar.utils :refer :all]
            [medley.core :as medley]
            ))


(defn calculate-change
  ([start-mobs]
   (zipmap classes (for [y classes](reduce + 0 (for [x (keys start-mobs)]
                                                 (* (x start-mobs) (x (y preference))))))))

  ([start-mobs quests]
   (zipmap classes (for [y classes](reduce + 0 (for [x (keys start-mobs) z items]
                                                 (+ (* (x start-mobs 0) (x (y preference) 0))
                                                    (* (z (x quests 0)0) (z (y reward 0)0)))
                                                 )))))
  )

(defn merge-quests
  [& quests]
  (let [quest-rewards (for [x quests] (hash-map (:mob x)(medley/map-vals #(/ % (:difficulty x)) (:rewards x))))]
    (apply deep-merge-with + quest-rewards)
  ))

(defn perform-player-turn!
  [state newquests]
  ()
  )


(defn attract
  ([start-players start-mobs]
   (let [change (calculate-change start-mobs)]
     (merge-with + start-players change)))
  ([start-players start-mobs quests]
   (let [quest-list (apply merge-quests quests)
         change (calculate-change start-mobs quest-list)]
      (merge-with + start-players change))))

(defn player-shift [savestate]
  (let [arglist (keep identity (list (:players savestate)
                                     (:mobs savestate)
                                     (:quests savestate)))]
    (assoc savestate :players (apply attract arglist))))

"
(def monsters [:ogres :harpies])

(defn calculate-change-first
  [start-world]
  (hash-map :warriors (+ (* (:ogres (:warriors preference)) (:ogres start-world))
                         (* (:harpies (:warriors preference)) (:harpies start-world)))
            :mages (+ (* (:ogres (:mages preference)) (:ogres start-world))
                      (* (:harpies (:mages preference)) (:harpies start-world)))))

(defn calculate-change-second
  [start-world]
  (hash-map :warriors (reduce + 0 (for [x monsters] (* (x start-world) (x (:warriors preference)))))
            :mages (reduce + 0 (for [x monsters] (* (x start-world) (x (:mages preference)))))))
"