(ns euler.p021
  (:use euler.numeric))

(defn solution {:answer 31626} []
  (sum (filter amicable? (range 10000))))



