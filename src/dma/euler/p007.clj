(ns dma.euler.p007
  (:use dma.euler.primes))

(defn solution {:answer 104743} []
  (nth (primes) (dec 10001)))


