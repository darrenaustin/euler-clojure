(ns euler.p007
  (:use euler.primes))

(defn solution {:answer 104743} []
  (nth (primes) (dec 10001)))


