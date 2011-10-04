(ns dma.euler.p145
  (:use dma.euler.numeric)
  (:refer-clojure :exclude [reversible?]))

;; Use a regular expression to match only odd digits.
;; While less readable, it is much faster than using
;; (every? odd? (digits n))
(defn all-odd? [n]
  (re-find #"^[1,3,5,7,9]+$" (str n)))

(defn reversible? [digits]
  (all-odd? (+ (number digits) (number (reverse digits)))))

(defn n-digit-range [num-digits]
  (when (< 0 num-digits)
    (range (Math/pow 10 num-digits))))

;; Only need to check candidates whose first
;; digit and last digit are opposite oddity
;; (i.e. odd, even or even, odd).  So we really
;; only need to check one set of those and mulitple
;; by two (if ab is reversible, then so is ba).
;;
(defn candidates [num-digits]
  (let [odd [1 3 5 7 9]
        even [2 4 6 8]
        num-middle-digits (- num-digits 2)
        middle-digits (n-digit-range num-middle-digits)]
    (if middle-digits
      (for [d1 odd dms middle-digits dn even]
        `[~d1 ~@(digits dms num-middle-digits) ~dn])
      (for [d1 odd dn even]
        [d1 dn]))))

;; We know there are no reversible 5 or 9 digit numbers, so we can
;; skip those. (TODO: Why do we know this other than previous brute
;; force searches?)
;;
;; This works, but is still too slow.
(defn solution {:answer nil} []
  (* 2 (sum (map #(count (filter reversible? (candidates %))) [2 3 4 6 7 8]))))
