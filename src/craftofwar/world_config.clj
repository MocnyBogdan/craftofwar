(ns craftofwar.world-config)

"Lists of entities"
(def classes #{:warriors :mages})
(def monsters #{:ogres :harpies})
(def items #{:xp :mace :wand})

"Configuration tables"
(def preference {:warriors {:harpies 1
                            :ogres 0.7}
                 :mages {:harpies 0.4
                         :ogres 0.8}})

(def reward {:warriors {:xp 1 :mace 10}
             :mages  {:xp 1}})