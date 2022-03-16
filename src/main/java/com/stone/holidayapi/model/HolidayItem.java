package com.stone.holidayapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.stone.holidayapi.domain.HolidayType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@ApiModel
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonDeserialize(
        builder = HolidayItem.Builder.class
)
public class HolidayItem {
    @JsonSerialize(
            using = LocalDateSerializer.class
    )
    @JsonDeserialize(
            using = LocalDateDeserializer.class
    )
    private final LocalDate date;
    private final String localName;
    private final String name;
    private final String countryCode;
    private final boolean fixed;
    private final boolean global;
    private final List<String> counties;
    private final Short launchYear;
    @ApiModelProperty(
            example = "PUBLIC",
            allowableValues = "PUBLIC, BANK, SCHOOL, AUTHORITIES, OPTIONAL, OBSERVANCE"
    )
    private final List<HolidayType> types;

    public HolidayItem(HolidayItem.Builder b) {
        this.date = b.date;
        this.localName = b.localName;
        this.name = b.name;
        this.countryCode = b.countryCode;
        this.fixed = b.fixed;
        this.global = b.global;
        this.counties = b.counties;
        this.launchYear = b.launchYear;
        this.types = b.types;
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocalName() {
        return localName;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<String> getCounties() {
        return counties;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public Short getLaunchYear() {
        return launchYear;
    }

    public List<HolidayType> getTypes() {
        return types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HolidayItem)) return false;
        HolidayItem that = (HolidayItem) o;
        return isFixed() == that.isFixed() && isGlobal() == that.isGlobal() && Objects.equals(getDate(), that.getDate()) && Objects.equals(getLocalName(), that.getLocalName()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCountryCode(), that.getCountryCode()) && Objects.equals(getCounties(), that.getCounties()) && Objects.equals(getLaunchYear(), that.getLaunchYear()) && Objects.equals(getTypes(), that.getTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getLocalName(), getName(), getCountryCode(), isFixed(), isGlobal(), getCounties(), getLaunchYear(), getTypes());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HolidayItem{");
        sb.append("date=").append(date);
        sb.append(", localName='").append(localName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", fixed=").append(fixed);
        sb.append(", global=").append(global);
        sb.append(", counties=").append(counties);
        sb.append(", launchYear=").append(launchYear);
        sb.append(", types=").append(types);
        sb.append('}');
        return sb.toString();
    }

    @JsonPOJOBuilder(
            withPrefix = ""
    )
    public static final class Builder {
        private LocalDate date;
        private String localName;
        private String name;
        private String countryCode;
        private boolean fixed;
        private boolean global;
        private List<String> counties;
        private Short launchYear;
        private List<HolidayType> types;

        private Builder() {
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder localName(String localName) {
            this.localName = localName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder fixed(boolean fixed) {
            this.fixed = fixed;
            return this;
        }

        public Builder global(boolean global) {
            this.global = global;
            return this;
        }

        public Builder counties(List<String> counties) {
            this.counties = counties;
            return this;
        }

        public Builder launchYear(Short launchYear) {
            this.launchYear = launchYear;
            return this;
        }

        public Builder types(List<HolidayType> types) {
            this.types = types;
            return this;
        }

        public HolidayItem build() {
            return new HolidayItem(this);
        }
    }
}
