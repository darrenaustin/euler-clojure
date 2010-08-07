(ns dma.euler.p041
  (:use [dma.euler numeric primes] clojure.contrib.combinatorics))

;; We can start with 7 digit numbers, because if the sum of a number's
;; digits are divisible by 3 the number itself is divisible by 3.
;;
;; A 9-digit pandigitial number would be divisible by 3:
;; 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45 -> div by 3, so never prime
;;
;; Same for 8-digit:
;; 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 36 -> div by 3, so never prime
;;
(defn solution {:answer 7652413} []
  (let [pandigitals (map number (permutations (reverse (range 1 8))))]
    (first (filter prime? pandigitals))))
