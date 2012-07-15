(ns dma.euler.p111
  (:use dma.euler.numeric dma.euler.primes))

(defn n-digit-primes [n]
  (let [max (exp 10 n)
        min (exp 10 (dec n))]
    (take-between min max (primes))))

(defn repeating-digits [n]
  (let [repeats (group-by identity (digits n))]
    (reduce (fn [m k]
              (let [num (count (m k))]
                (if (< 1 num)
                  (assoc m k num)
                  (dissoc m k))))
              repeats (keys repeats))))

(defn update-max-digit-map [m n]
  (let [repeat-digits (repeating-digits n)]
    (reduce (fn [m [digit repeats]]
              (let [max-repeats (get-in m [digit :max] 0)]
                (cond (< repeats max-repeats) m
                      (= repeats max-repeats) (update-in m [digit :sum] + n)
                      :else (assoc m digit {:max repeats :sum n}))))
            m repeat-digits)))

;; TODO: get this working
(defn solution- {:answer nil} []
  (let [max-digit-map (reduce update-max-digit-map {} (n-digit-primes 10))]
    (sum (map #(get-in max-digit-map [% :sum]) (range 10)))))

