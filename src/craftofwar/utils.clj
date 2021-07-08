(ns craftofwar.utils)

(defn deep-merge-with
  ([f])
  ([f a] (f a))
  ([f a b]
   (when (or a b)
     (letfn [(merge-entry [m e]
               (let [k  (key e)
                     v' (val e)]
                 (if (contains? m k)
                   (assoc m k (let [v (get m k)]
                                (if (and (map? v) (map? v'))
                                  (deep-merge-with f v v')
                                  (f v v'))))
                   (assoc m k v'))))]
       (reduce merge-entry (or a {}) (seq b)))))
  ([f a b & more]
   (reduce (fn [x y] (deep-merge-with f x y)) (or a {}) (cons b more))))

