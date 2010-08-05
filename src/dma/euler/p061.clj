(ns dma.euler.p061
  (:use dma.euler.numeric))

;; TODO: find a cleaner way to do this

(defn triangle   [n] (/ (* n (inc n)) 2))
(defn pentagonal [n] (/ (* n (dec (* 3 n))) 2))
(defn hexagonal  [n] (* n (dec (* 2 n))))
(defn heptagonal [n] (/ (* n (- (* 5 n) 3)) 2))
(defn octagonal  [n] (* n (- (* 3 n) 2)))

(defn first-digits [n] (quot n 100))
(defn last-digits [n] (rem n 100))
(defn full-num [f l] (+ (* 100 f) l))

(defn four-digit-map [f]
  (reduce
   (fn [m n]
     (let [start (first-digits n)
           end   (last-digits n)
           other-ends (m start)]
       (if (> end 9)
         (assoc m start (conj other-ends end))
         m)))
   {}
   (take-between 1000 10000 (map f (whole-nums)))))

(defn find-first [col]
  (first (filter identity col)))

(defn smallest-set [sets]
  (reduce (fn [min-s s]
            (if (< (count s) (count min-s)) s min-s))
          sets))

(defn find-cycles [last-end types cycle]
  (if (empty? types)
    (let [start (first-digits (first cycle))
          end (last-digits (last cycle))]
      (when (= start end)
        cycle))
    (find-first
     (for [t types]
       (find-first
        (for [next-end (t last-end)]
          (find-cycles next-end (disj types t) (conj cycle (full-num last-end next-end)))))))))

(defn solution {:answer 28684} []
  (let [types (sorted-set-by #(< (count %1) (count %2))
                             (four-digit-map triangle)
                             (four-digit-map square)
                             (four-digit-map pentagonal)
                             (four-digit-map hexagonal)
                             (four-digit-map heptagonal)
                             (four-digit-map octagonal))
        start-type (first types)
        types-left (disj types start-type)
        starts (keys start-type)]
    (sum
     (find-first
      (for [start starts]
        (find-first
         (for [end (start-type start)]
           (find-cycles end types-left [(full-num start end)]))))))))

