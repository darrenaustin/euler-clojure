(ns dma.euler.p043
  (:use [dma.euler numeric util] clojure.set))

(defn special-pans [base unused-digits divisors]
  (if (empty? divisors)
    (if (not (zero? (first unused-digits)))
      [(number (conj base (first unused-digits)))]
      [])
    (collect
     (for [d unused-digits
           :let [new-base (conj base d)]
           :when (div? (number (subvec (vec new-base) 0 3)) (first divisors))]
       (special-pans new-base (disj unused-digits d) (rest divisors))))))

(defn solution {:answer 16695334890} []
  (sum
   (collect
    (for [base (range 17 1000 17)
          :let [base-digits (if (< base 100) (conj (digits base) 0) (digits base))
                unused-digits (difference (set (range 0 10)) (set base-digits))]
          :when (= (count unused-digits) 7)]
      (special-pans base-digits unused-digits [13 11 7 5 3 2])))))

;;
;; This was my correct, simple but very slow brute force solution
;;
;; (defn special-pan? [n]
;;   (let [ds (vec (digits n))]
;;     (and (= (count ds) 10)
;;          (every? identity (map div? (map number (rest (partition 3 1 ds)))
;;                                     [2 3 5 7 11 13 17])))))
;;
;; (defn solution {:answer 16695334890} []
;;   (sum (filter special-pan? (map number (permutations (reverse (range 10)))))))

