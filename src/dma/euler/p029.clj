(ns dma.euler.p029
  (:use dma.euler.numeric))

(defn solution {:answer 9183} []
  (count
   (into #{}
         (for [a (range 2 101) b (range 2 101)]
           (exp a b)))))

