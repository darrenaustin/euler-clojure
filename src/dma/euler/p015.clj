(ns dma.euler.p015
  (:use dma.euler.numeric clojure.contrib.def))

(defn-memo num-paths [x y]
  (if (or (zero? x) (zero? y))
    1
    (+ (num-paths (dec x) y)
       (num-paths x (dec y)))))

(defn solution {:answer 137846528820} []
  (num-paths 20 20))
