import React, { useState } from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TablePagination
} from "@mui/material";

const LocationTable = ({ locations }) => {
    const [open, setOpen] = useState(false);
    const [selectedLocation, setSelectedLocation] = useState(null);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    if (!locations || !Array.isArray(locations) || locations.length === 0) {
        return <div>No locations to display.</div>;
    }

    const flattenedLocations = locations.flat();

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const sliceStart = page * rowsPerPage;
    const sliceEnd = sliceStart + rowsPerPage;

    const embedUrl = `https://www.google.com/maps/embed/v1/place?key=AIzaSyBYlu3BaRKJK_ggoXNEwRrdRHzYxcEVkPs&q=${selectedLocation?.latitude},${selectedLocation?.longitude}`;    return (
        <div
            style={{
                width: '75%',
                padding: '16px',
                borderRadius: '8px',
                margin: 'auto',
                marginTop: '50px',
                boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.5)',
            }}
        >
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Category</TableCell>
                            <TableCell>Longitude</TableCell>
                            <TableCell>Latitude</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {flattenedLocations.slice(sliceStart, sliceEnd).map((location, index) => (
                            <TableRow key={index}>
                                <TableCell>{location.name}</TableCell>
                                <TableCell>{location.category}</TableCell>
                                <TableCell>{location.longitude}</TableCell>
                                <TableCell>{location.latitude}</TableCell>
                                <TableCell>
                                    <Button onClick={() => {
                                        setSelectedLocation(location);
                                        setOpen(true);
                                    }}>
                                        Open Map
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <TablePagination
                rowsPerPageOptions={[10, 25, 50]}
                component="div"
                count={flattenedLocations.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
            <Dialog open={open} onClose={() => setOpen(false)}>
                <DialogTitle>{selectedLocation?.name} Map</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        <iframe
                            title="Google Map"
                            width="100%"
                            height="300"
                            frameBorder="0"
                            style={{ border: 0 }}
                            src={embedUrl}
                            allowFullScreen
                        ></iframe>
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpen(false)}>Close</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default LocationTable;