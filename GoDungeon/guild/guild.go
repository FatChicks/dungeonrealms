package guild

type Guild struct {
	ID   int `json:"id:"`
	Name string `json:"name"`
	Tag  string `json:"tag"`
	Gems int `json:"gems"`
}