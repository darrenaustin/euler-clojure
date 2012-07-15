(ns dma.euler.p206
  (:use dma.euler.numeric clojure.math.numeric-tower))

;; Analyzing the problem, we know that the target number must end
;; in a 0 in order for the square to end in a 0.  Also the next
;; digit must be either a 3 or a 7 in order to get a 9 the
;; hundreds position.  Therefore we only have to test 10 ^ 9 + 30
;; and 10 ^ 9 + 70 and increment by 100, testing the squares
;; along the way.

(defn matching-squares [candidates]
  (let [pattern #"1\d2\d3\d4\d5\d6\d7\d8\d9\d0"]
    (first (filter #(re-find pattern (str (square %))) candidates))))

(defn solution {:answer 1389019170} []
  (let [upper-bound (int (ceil (sqrt 1929394959697989990)))
        start (expt 10 9)]
     (or (matching-squares (range (+ start 30) upper-bound 100))
         (matching-squares (range (+ start 70) upper-bound 100)))))

