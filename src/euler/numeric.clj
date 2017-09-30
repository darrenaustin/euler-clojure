(ns euler.numeric)

(defn sum [col] (apply + col))
(defn product [col] (apply * col))
(defn square [x] (* x x))
(defn cube [x] (* x x x))

(defn div? [n d] (zero? (rem n d)))

(defn whole-nums [] (iterate inc 0))
(defn natural-nums [] (rest (whole-nums)))

(defn fibs
  ([] (fibs 0 1))
  ([a b] (lazy-seq (cons a (fibs b (+' a b))))))

(defn triangle-nums
  ([] (triangle-nums 0 1))
  ([s n] (lazy-seq (cons (+ s n) (triangle-nums (+ s n) (inc n))))))

(defn take-while-< [max col]
  (take-while #(< % max) col))

(defn drop-while-< [max col]
  (drop-while #(< % max) col))

(defn take-between [min max col]
  (take-while #(< % max) (drop-while #(< % min) col)))

(defn digit->int [digit]
  (- (int digit) (int \0)))

(defn digits
  ([n] (map digit->int (str n)))
  ([n num-digits]
     (let [ds (digits n) used (count ds)]
       (if (> num-digits used)
         ;; pad the digit sequence with enough leading 0s to fill
         ;; num-digits
         (concat (repeat (- num-digits used) 0) ds)
         ds))))

(defn number [digits]
  (read-string (apply str (cons "10r" digits))))

(defn num-digits [n]
  (count (str n)))

(defn palindrome? [x]
  (let [s (seq (str x))]
    (= s (reverse s))))

(defn n-digits [n]
  (when (> n 0)
    (range (int (Math/pow 10 (dec n)))
           (int (Math/pow 10 n)))))

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn lcm [a b]
  (/ (* a b) (gcd a b)))

;; TODO: use the expt from math.numeric-tower?
(defn exp [x n]
  (apply *' (take n (repeat x))))

(defn factorial [n]
  (apply *' (range 2 (inc n))))

(defn proper-divisors [n]
  (let [end (int (Math/sqrt n))]
    (loop [ds [1] current 2]
      (cond
       (> current end) ds
       (div? n current)
         (let [factor (/ n current)]
           (if (= factor current)
             (recur (conj ds factor) (inc current))
             (recur (conj ds current factor) (inc current))))
       :else (recur ds (inc current))))))

;; Cleaner, but slower version than above looping construct
;; (defn proper-divisors [n]
;;   (apply concat [1]
;;          (for [d (filter #(div? n %) (range 2 (inc (floor (Math/sqrt n)))))]
;;            (let [f (/ n d)]
;;              (if (= f d) [d] [d f])))))

(defn divisors [n]
  (conj (proper-divisors n) n))

(defn sum-divisors [n]
  (sum (proper-divisors n)))

(defn perfect? [n]
  (= n (sum-divisors n)))

(defn abundant? [n]
  (> (sum-divisors n) n))

(defn deficient? [n]
  (< (sum-divisors n) n))

(defn amicable? [a]
  (let [b (sum-divisors a)]
    (and (not= a b)
         (= a (sum-divisors b)))))

;; (defn intersect [as bs]
;;   "Intersect two sorted sequences of numbers"
;;   (cond
;;    (nil? as) nil
;;    (nil? bs) nil
;;    (= (first as) (first bs))
;;      (conj (intersect (rest as) (rest bs)) (first as))
;;    (> (first as) (first bs))
;;      (intersect as (rest bs))
;;    :else
;;          (intersect (rest as) bs)))
