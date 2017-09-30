(ns euler.p204
  (:use [euler numeric primes]))

(defn hamming? [n max-prime-factor]
  (every? #(<= % max-prime-factor) (prime-factors n)))

(defn solution {:answer nil} []
  (count
   (take-while-< (exp 10 5) (filter #(hamming? % 5) (natural-nums)))))
