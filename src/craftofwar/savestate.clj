(ns craftofwar.savestate
  (:require [craftofwar.change :refer :all]
            [craftofwar.sample-data :as sample]))

(def savestate (atom sample/savestate))

(defn save-game
  ([file-name save-state] (spit file-name save-state) file-name)
  ([file-name] (save-game file-name @savestate))
  ([] (save-game "save.txt")))

(defn load-game!
  ([file-name] (reset! savestate (read-string (slurp file-name))))
  ([] (load-game! "save.txt"))
  )
