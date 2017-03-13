(ns connect-clojure-api.core
  (:require [cljs.nodejs :as nodejs]
            [connect-clojure-api.connect :refer [connect]]))

(defn start []
  (let [api (nodejs/require "./api")]
    (aset api "connect" connect)))

(set! *main-cli-fn* start)
