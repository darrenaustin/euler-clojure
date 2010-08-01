(ns dma.euler.p020
  (:use dma.euler.numeric))

(defn solution {:answer 648} []
  (sum (digits (factorial 100))))


