import React  from 'react';

class StationComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            Stations : []
        };
        
        this.url = "http://localhost:8080";
        this.showAll = this.showAll.bind(this);
        this.addStation = this.addStation.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    componentDidMount() {
        this.showAll();
        // this.addStation();
    }
    handleChange(changeObject) {
        this.setState(changeObject);
    }

    showAll() {
        fetch(this.url)
        .then(res => res.json())
        .then(
            (Stations) => {
                this.setState({ Stations: Stations.Data });
            },
            (error) => {
                alert(error);
            }
        )
    }

    addStation(e){
        let station = {};
        station.station_name = this.state.station_name;
        station.station_pricing = this.state.station_pricing;
        station.station_address = this.state.station_address;
        fetch(this.url, {
            "method": "POST",
            "headers": {
                'Content-Type': 'application/json'
            },
            "body": JSON.stringify(station)
        })
        .then(response => response.json())
        .then(response => {
            console.log(response)
        })
        .catch(err => {
            console.log(err);
        });
        alert("station Added successfully");
        this.showAll();
    }

    render() {
        return (
            <div className="container">
                <div className='stationHeading'>
                    <center><h1><b></b>All Charging Stations</h1></center>
                </div>
                <table>
                    <thead className='th'>
                        <tr>
                            <th>Station Id</th>
                            <th>Station Name</th>
                            <th>Station Address</th>
                            <th>Station Pricing</th>
                        </tr>
		            </thead>
		            <tbody className='tr'>
			            {this.state.Stations.map(Stations =>
                            <tr>
                                <td>{Stations.station_id}</td>
                                <td>{Stations.station_name}</td>
                                <td>{Stations.station_address}</td>
                                <td>{Stations.station_pricing}</td>
                            </tr>
                        )}
		            </tbody>
	            </table>

                <div className='addForm'>
                    <form className="d-flex flex-column">
                        <legend className="text-center">Add a Charging Station</legend>
                        <label htmlFor="name" className='col-sm-4'>
                            Station Name:
                            <input
                                name="name"
                                id="name"
                                type="text"
                                className="form-control"
                                value={this.state.station_name}
                                onChange={(e) => this.handleChange({ station_name: e.target.value })}
                                required
                            />
                        </label>
                        <label htmlFor="address" className='col-sm-4'>
                            Station Address:
                            <input
                                name="address"
                                id="address"
                                type="text"
                                className="form-control"
                                value={this.state.station_address}
                                onChange={(e) => this.handleChange({ station_address: e.target.value })}
                                required
                            />
                        </label>
                        <label htmlFor="price" className='col-sm-2'>
                            Station Price:
                            <input
                                name="price"
                                id="price"
                                type="number"
                                className="form-control"
                                value={this.state.station_pricing}
                                onChange={(e) => this.handleChange({ station_pricing: e.target.value })}
                            />
                        </label>
                        <div className='col-sm-2'>
                            <button className="btn btn-primary btn-group-justified" type='button' onClick={(e) => this.addStation(e)}>
                            Add Charging Station
                            </button>
                        </div>
                
                    </form>
                </div>
            </div>
        );
    }
}
export default StationComponent;