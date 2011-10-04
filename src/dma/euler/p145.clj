(ns dma.euler.p145
  (:use dma.euler.numeric)
  (:refer-clojure :exclude [reversible?]))

;; Naive brute force solution works great for small numbers, but
;; becomes prohibitive with large numbers
;;
(defn reversible? [n]
  (and (not (zero? (mod n 10)))
   (every? odd? (digits (+ n (number (reverse (digits n))))))))

(defn solution {:answer nil} []
  (count (filter rev? (range 1000))))
