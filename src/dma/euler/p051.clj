(ns dma.euler.p051
  (:use [dma.euler numeric primes]))

(defn n-digit-primes [n primes]
  (apply sorted-set (map str (take-between (exp 10 (dec n)) (exp 10 n) primes))))

(defn n-digit-masks [n]
  (map #(let [m (Integer/toBinaryString %), ds (count m)]
          (if (< ds n)
            (concat (repeat (- n ds) \0) m)
            m))
       (range 1 (exp 2 n))))

(defn matches-mask? [ds mask]
  (apply = (filter identity (map #(if (= \1 %2) %1) ds mask))))

(defn apply-mask [ds mask n]
  (apply str (map #(if (= \1 %2) n %1) ds mask)))

(defn prime-family [p mask prime-str-set]
  (let [ds (digits p)]
    (when (matches-mask? ds mask)
      (filter prime-str-set (map #(apply-mask ds mask %) (range 10))))))

(defn search [num-digits family-size primes]
  (let [prime-str-set (n-digit-primes num-digits primes)
        masks (n-digit-masks num-digits)]
    (first
     (for [p prime-str-set
           m masks
           :let [family (prime-family p m prime-str-set)]
           :when (= (count family) family-size)]
       (apply min (map number family))))))

(defn solution {:answer 121313} []
  (let [ps (primes)]
    (loop [n 2]
      (or (search n 8 ps)
          (recur (inc n))))))
