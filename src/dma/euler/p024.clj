(ns dma.euler.p024
  (:use dma.euler.numeric clojure.contrib.combinatorics))

;; TODO: Ok, this is cheating.  I should really look into writing my
;; own perm method
(defn solution {:answer 2783915460} []
  (number (nth (lex-permutations (range 0 10)) (dec 1000000))))
