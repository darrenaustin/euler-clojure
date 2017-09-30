(ns euler.p039
  (:use euler.numeric))

;; TODO: check out the Primitive Pythagorean Triplets (described in the forum)
;; and described at:
;; http://mathworld.wolfram.com/PythagoreanTriple.html

(defn num-solutions [p]
  (sum
   (for [a (range 1 (inc (/ p 4)))
         :let [a2 (square a), pa (- p a)]
         b (range (inc a) (inc pa))
         :let [c (- pa b)]]
     (if (= (square c) (+ a2 (square b)))
       1 0))))

(defn solution {:answer 840} []
  (first (reduce (fn [max n] (if (< (second max) (second n)) n max))
                 (map (fn [p] [p (num-solutions p)]) (range 12 1001 2)))))
