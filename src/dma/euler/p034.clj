(ns dma.euler.p034
  (:use dma.euler.numeric))

(def factorials (vec (map factorial (range 10))))

(defn solution {:answer 40730} []
  (sum (filter #(= (sum (map factorials (digits %))) %)
               (range 11 9999999 2))))
