(ns euler.p020
  (:use euler.numeric))

(defn solution {:answer 648} []
  (sum (digits (factorial 100))))


