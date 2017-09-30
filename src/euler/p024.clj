(ns euler.p024
  (:use euler.numeric [clojure.math.combinatorics :as combo]))

;; TODO: Ok, this is cheating.  I should really look into writing my
;; own perm method
(defn solution {:answer 2783915460} []
  (number (nth (combo/permutations (range 0 10)) (dec 1000000))))
