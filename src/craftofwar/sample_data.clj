(ns craftofwar.sample-data)

(def players {:warriors 30 :mages 20})
(def mobs {:harpies 10 :ogres 20})

(def infrastructure
  {:gold 2000
   :raw-materials
         {:broken-weapons 73
          :monster-parts 9
          :humanoid-parts 12
          }
   :processed-materials
         {:building-materials 10
          :fuel 20
          :weapon-parts 30
          :frost-components 5
          :leather 10
          }
   :items
         {:mace 10
          :wand 10
          }
   :factories
         {:weapon-recycler 1
          :blacksmith 1
          }
   })


(def quest1 {:name "Kill Harpies" :mob :harpies :difficulty 10 :rewards {:xp 1000 :mace 10}})
(def quest2 {:name "Kill Harpy Queen" :mob :harpies :difficulty 20 :rewards {:xp 1000 :wand 15}})
(def quest3 {:name "Kill Ogre Queen" :mob :ogres :difficulty 50 :rewards {:xp 5000 :mace 50}})
(def quests {:harpies {:xp 150 :mace 1 :wand 3/4}})
(def quests2 {:harpies {:xp 150 :mace 1 :wand 3/4} :ogres {:xp 100 :mace 1}})
(def quest-list #{quest1 quest2 quest3})

(def savestate {:players players :mobs mobs :quests quest-list :infrastructure infrastructure})