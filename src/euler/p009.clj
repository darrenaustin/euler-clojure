(ns euler.p009
  (:use euler.numeric))

(defn pythagorean-c [a b]
  (Math/sqrt (+ (* a a) (* b b))))

(defn triplet-for-sum [n]
  (first
   (for [a (range 2 (int (/ n 2)))
         b (range a n)
         :when (== n (+ a b (pythagorean-c a b)))]
     [a b (int (pythagorean-c a b))])))

(defn solution {:answer 31875000} []
  (product (triplet-for-sum 1000)))
