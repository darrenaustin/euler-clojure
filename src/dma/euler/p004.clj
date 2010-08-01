(ns dma.euler.p004
  (:use dma.euler.numeric))

(defn solution {:answer 906609} []
  (reduce max (filter palindrome? (for [x (range 100 1000) y (range x 1000)] (* x y)))))
