(ns craftofwar.core-test
  (:require [clojure.test :refer :all]
            [craftofwar.webfront :refer :all]
            [craftofwar.core :refer :all]
            [craftofwar.change :refer :all]
            [craftofwar.savestate :refer :all]
            [craftofwar.sample-data :as sample]))

(def test-players (hash-map :warriors 10 :mages 10))
(def world1 (hash-map :harpies 20 :ogres 1))

(deftest attract-increases-players
  (testing "increases warriors more if more harpies")
  (is (pos? (- (:warriors (attract test-players world1)) (:warriors test-players))))
  )

(deftest calculating-change
  (testing "calculates change for sample-mobs"
    (is (number? (:warriors (calculate-change world1)))))
  (testing "calculates change for 1 mob"
    (is (number? (:warriors (calculate-change (hash-map :ogres 4))))))
  (testing "calculates change for sample-mobs and quests"
    (is (number? (:warriors (calculate-change world1 sample/quests2)))))
  (testing "calculate change for 1 mob and 1 sample-quest"
      (is (number? (:warriors (calculate-change (hash-map :ogres 4) sample/quests2)))))
  (testing "quest creates more change"
    (is (< (:warriors (calculate-change (hash-map :ogres 4)))(:warriors (calculate-change sample/mobs sample/quests)))))
    )

(deftest merge-quest-test
  (testing "merge quests merges 2 quests into correct format and values"
  (is (= (merge-quests sample/quest1 sample/quest2) sample/quests)))
  (testing "merge quests merges 3 quests into correct format and values"
  (is (= (merge-quests sample/quest1 sample/quest2 sample/quest3) sample/quests2)))
  )
;
;(deftest player-turn-test
;  (testing "player turn updates the world state"
;    (is (!= (player-turn state statechange) state))
;    )
;
;  )


(deftest reading-and-saving
  (testing "saving and reading is identity"
    (is (load-game! (save-game "testsave.txt" world1))))
    )

(run-tests)
