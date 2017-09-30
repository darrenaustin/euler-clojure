(ns euler.p047
  (:use [euler numeric primes]))

(defn distinct-prime-factors [n primes]
  (map #(apply * %) (prime-factorization n primes)))

(defn solution {:answer 134043} []
  (let [ps (primes)]
    (loop [a [1 (distinct-prime-factors 1 ps)]
           b [2 (distinct-prime-factors 2 ps)]
           c [3 (distinct-prime-factors 3 ps)]
           d [4 (distinct-prime-factors 4 ps)]]
      (let [factors (concat (second a) (second b) (second c) (second d))]
        (if (= 16 (count factors) (count (distinct factors)))
          (first a)
          (recur b c d [(inc (first d)) (distinct-prime-factors (inc (first d)) ps)]))))))

;; More functional, but slightly slower solution
;;
;; (defn solution {:answer 134043} []
;;   (let [ps (primes)]
;;     (ffirst
;;      (first
;;       (filter (fn [[[a af] [b bf] [c cf] [d df]]]
;;                 (let [factors (concat af bf cf df)]
;;                   (= 16 (count factors) (count (distinct factors)))))
;;               (partition 4 1
;;                          (map (fn [n] [n (distinct-prime-factors n ps)]) (natural-nums))))))))

