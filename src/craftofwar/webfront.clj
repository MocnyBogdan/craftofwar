(ns craftofwar.webfront
      (:require [hiccup.page :refer [html5]]
                [craftofwar.world-config :refer :all]
                [craftofwar.sample-data :as sample]
  ))

(def class-display-names {:warriors "Warriors" :mages "Mages"})
(def mob-display-names {:ogres "Ogres" :harpies "Harpies"})
(def reward-display-names {:xp "Experience" :mace "Mace" :wand "Wand"})

(defn web [game-state]
  {:status 200
   :body
           (html5 [:head
                   [:title "Craft of Warworld"]]
                  [:body
                   [:h1 "World"]

                   [:form {:action "/next_turn" :method "GET"}
                    [:div (for [x classes] (vector :label (x class-display-names)
                                                   [:input {:name     (x class-display-names)
                                                            :type     "number"
                                                            :disabled "disabled"
                                                            :value    (x (:players game-state))}]))]
                    [:h2 "Quests"]
                    (for [x (:quests game-state)]

                      [:div
                       [:h3 "Quest"]
                       (vector :div (vector :label "Quest"
                                            [:input {:name     (:name x)
                                                     :type     "string"
                                                     :disabled "disabled"
                                                     :value    (:name x)}]))
                       (vector :div (vector :label (:mob x)
                                            [:input {:name     ((:mob x) mob-display-names)
                                                     :type     "string"
                                                     :disabled "disabled"
                                                     :value    ((:mob x) mob-display-names)}]))
                       (vector :div (vector :label (:difficulty x)
                                            [:input {:name     "difficulty"
                                                     :type     "number"
                                                     :disabled "disabled"
                                                     :value    (:difficulty x)}]))


                       (for [y (keys (:rewards x))] [:div (vector :div (vector :label (y reward-display-names)
                                                                               [:input {:name     (y reward-display-names)
                                                                                        :type     "number"
                                                                                        :disabled "disabled"
                                                                                        :value    (y (:rewards x))}]))])
                       ])



                    [:div [:button "Next Turn"]]
                    [:script]
                    ]

                   [:h2 "Input Quests"]
                   [:div [:form {:action "/new_quest" :method "POST"}


                     (vector :div (vector :label "Quest"
                                               [:input {:name "name"
                                                        :id "name"
                                                        :type "string"}]))
                     (vector :div (vector :label "Input Mob"
                                          [:input {:name "mob"
                                                   :id "mob"
                                                   :type "string"}]))
                     (vector :div (vector :label "Input Difficulty"
                                          [:input {:name "difficulty"
                                                   :id "diff"
                                                   :type "number"}]))
                          (vector :div (vector :label "Quest Reward 1"
                                               [:input {:name "quest reward 1"
                                                        :id "qr1"
                                                        :type "string"}]))
                          (vector :div (vector :label "Value 1"
                                               [:input {:name "value 1"
                                                        :id "val1"
                                                        :type "number"}]))
                          (vector :div (vector :label "Quest Reward 2"
                                               [:input {:name "quest reward 2"
                                                        :id "qr2"
                                                        :type "string"}]))
                          (vector :div (vector :label "Value 2"
                                               [:input {:name "value 2"
                                                        :id "val2"
                                                        :type "number"}]))
                     [:input {:type "submit" :value "Add quest"}]
                    ]]
                   ]
                  )}
  )
