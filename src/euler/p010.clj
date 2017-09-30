(ns euler.p010
  (:use [euler numeric primes]))

(defn solution {:answer 142913828922} []
  (sum (take-while-< 2000000 (primes))))





