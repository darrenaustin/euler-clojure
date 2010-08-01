(ns dma.euler.p002
  (:use dma.euler.numeric))

(defn solution {:answer 4613732} []
  (sum (take-while-< 4000000 (filter even? (fibs 1 2)))))
