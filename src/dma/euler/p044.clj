(ns dma.euler.p044
  (:use dma.euler.numeric clojure.contrib.math))

(defn pentagonal [n]
  (/ (* n (dec (* 3 n))) 2))

;; TODO: guessed at the upperbound.  How could you determine this without guessing?
(defn solution {:answer 5482660} []
  (let [pents (vec (map pentagonal (range 1 3000)))
        pentagonal? (set pents)]
    (first
     (for [a (range (count pents))
           b (range a (count pents))
           :let [pa (nth pents a), pb (nth pents b)]
           :when (and (pentagonal? (- pb pa)) (pentagonal? (+ pa pb)))]
       (- pb pa)))))
