(ns dma.euler.p097
  (:use dma.euler.numeric))

;; So the tricky part of this problem is doing the power expansion
;; without allowing the numbers to get prohibitively large.  Because
;; we only need the last 10 digits, we can manually expand the powers
;; but strip each iteration to only the last 10 digits.
;;
;; We abstract the stripping of the last ten digits by passing
;; in a function to do the stripping to the power function.
;;
;; We also optimize this by taking advantage of the fact that:
;; x ^ n = (x * x) ^ (n / 2) for even n.  This dramatically
;; reduces the number of times through the expansion.
(defn power [x, n, limit]
  (cond (= n 0) 1
        (= n 1) (limit x)
        (even? n) (recur (limit (* x x)) (/ n 2) limit)
        :else (limit (* x (power (limit (* x x)) (/ (dec n) 2) limit)))))

;; A helper function that creates a function that will
;; return only the last n digits of the number
(defn last-n-digits [n]
  (let [limit (bigint (Math/pow 10 n))]
    (fn [x] (rem x limit))))

(defn solution {:answer 8739992577} []
  (let [last-10 (last-n-digits 10)]
    (last-10 (inc (* 28433 (power 2 7830457 last-10))))))
