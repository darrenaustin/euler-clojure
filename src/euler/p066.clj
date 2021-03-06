(ns euler.p066
  (:require [euler.numeric :as n]
            [clojure.math.numeric-tower :as math]))

;; Failed brute force attempt.  Need to do some research:
;; http://en.wikipedia.org/wiki/Pell's_equation
;; http://en.wikipedia.org/wiki/Continued_fraction

(defn y[D x]
  (let [y2 (/ (dec (* x x)) D)
        y (bigint (math/sqrt y2))]
    (when (= y2 (* y y))
      y)))

(defn square? [n]
  (let [nr (bigint (math/sqrt n))]
    (= (* nr nr) n)))

(defn solution {:answer nil} []
  (apply max
   (for [D (range 1 1001)
         :when (not (square? D))
         :let [x (first (filter #(when (y D %) %) (iterate inc 2)))]]
     x)))
