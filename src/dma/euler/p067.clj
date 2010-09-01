(ns dma.euler.p067
  (:use [clojure.contrib.duck-streams :only [read-lines]]))

(def *large-triangle*
     (into [] (for [line (read-lines "data/triangle.txt")]
                (into [] (map #(Integer/parseInt %) (.split line " "))))))

(defn width [triangle row]
  (count (triangle row)))

(defn height [triangle]
  (count triangle))

(defn value [triangle x y]
  ((triangle y) x))

(defn set-value [triangle x y value]
  (assoc triangle y (assoc (nth triangle y) x value)))

(defn propagate-row [triangle y]
  (let [row (inc y)]
    (loop [t triangle, x (dec (width t y))]
      (if (neg? x)
        t
        (let [left (value t x row)
              right (value t (inc x) row)
              val (+ (value t x y) (max left right))]
          (recur (set-value t x y val) (dec x)))))))

(defn propagate-rows [triangle]
  (loop [t triangle, y (- (height t) 2)]
    (if (neg? y)
      t
      (recur (propagate-row t y) (dec y)))))

(defn solution {:answer 7273} []
  (value (propagate-rows *large-triangle*) 0 0))
