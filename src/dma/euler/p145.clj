(ns dma.euler.p145
  (:use dma.euler.numeric)
  (:refer-clojure :exclude [reversible?]))

;; Use a regular expression to match only odd digits.  While less
;; readable, it is much faster than using (every? odd? (digits n))
(defn all-odd? [n]
  (re-find #"^[1,3,5,7,9]+$" (str n)))

(defn reverse-digits [n]
  (loop [r 0 n n]
    (if (zero? n)
      r
      (recur (+ (* 10 r) (rem n 10)) (quot n 10)))))

(defn reversible? [n]
  (all-odd? (+ n (reverse-digits n))))

(defn n-digit-range [num-digits]
  (when (< 0 num-digits)
    (range (Math/pow 10 num-digits))))

;; Only need to check candidates whose first digit and last digit are
;; opposite oddity (i.e. odd, even or even, odd).  So we really only
;; need to check one set of those and mulitple by two (if ab is
;; reversible, then so is ba).
;;
(defn candidates [num-digits]
  (let [odd (map #(apply * % (repeat (dec num-digits) 10)) [1 3 5 7 9])
        even [2 4 6 8]
        middle-digits (n-digit-range (- num-digits 2))]
    (if middle-digits
      (for [d1 odd dms middle-digits dn even]
        (+ d1 (* dms 10) dn))
      (for [d1 odd dn even]
        (+ d1 dn)))))

;; We know there are no reversible 5 or 9 digit numbers, so
;; we can skip those. (But we only know this because of previous
;; really slow brute force solutions)
(defn solution {:answer nil} []
  (* 2 (sum (map #(count (filter reversible? (candidates %))) [2 3 4 6 7 8]))))
