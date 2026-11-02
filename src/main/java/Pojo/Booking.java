package Pojo;


public class Booking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    private BookingDates bookingdates;


// Getters and Setters ...

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public Integer getTotalprice() {

        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {

        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {

        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {

        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {

        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {

        this.additionalneeds = additionalneeds;
    }

    public BookingDates getBookingdates() {

        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {

        this.bookingdates = bookingdates;
    }

    //    Inner class

    public static class BookingDates {

        private String checkin;
        private String checkout;

        public String getCheckin() {

            return checkin;
        }

        public void setCheckin(String checkin) {

            this.checkin = checkin;
        }

        public String getCheckout() {

            return checkout;
        }

        public void setCheckout(String checkout) {

            this.checkout = checkout;

        }
    }
}

