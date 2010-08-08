(ns dma.euler.p051
  (:use dma.euler.numeric))

(defn same-digits? [nums]
  (apply = (map #(sort (digits %)) nums)))

(defn solution {:answer 142857} []
  (ffirst
   (filter same-digits?
           (map (fn [x] (map * (repeat x) (range 1 7))) (natural-nums)))))

