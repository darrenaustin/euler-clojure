(ns dma.euler.p003
  (:use [dma.euler numeric primes]))

(defn solution {:answer 6857} []
  (reduce max (prime-factors 600851475143)))
